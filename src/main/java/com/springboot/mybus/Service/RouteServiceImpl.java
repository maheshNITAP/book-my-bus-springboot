package com.springboot.mybus.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.mybus.dto.RouteDTO;
import com.springboot.mybus.model.Bus;
import com.springboot.mybus.model.Route;
import com.springboot.mybus.repository.BusRepository;
import com.springboot.mybus.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class RouteServiceImpl implements  RouteService{

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    BusRepository busRepository;

    @Autowired
    ObjectMapper objectMapper;

//    @Override
//    public List<Route> findAll() {
//        return routeRepository.findAll();
//    }
//
//    @Override
//    public Route save(Route route) {
//    return  routeRepository.save(route);
//    }
//
//    @Override
//    public Optional<Route> findById(Integer theId) {
//        return routeRepository.findById(theId);
//    }
//
//    @Override
//    public void deleteById(Integer theId) {
//        routeRepository.deleteById(theId);
//    }

    @Override
    public List<Bus> getBusesBySourceAndDestination(String origin, String destination, String date) {
            List<Integer> validBusIds=routeRepository.getBusBysourceDestination(origin, destination, date);
            List<Bus> buses =new ArrayList<>();
            if(!CollectionUtils.isEmpty(validBusIds)){
                validBusIds.forEach(busId -> {
                    Optional<Bus> bus =busRepository.findById(busId);
                    bus.ifPresent(buses::add);
                });
            }
            return buses;
    }

//    @Override
//    public HashSet<String> getSeatsBySourceAndDestination(String origin, String destination, Integer busId,String date)
//    {
//        HashSet<String> totalSeats = new HashSet<>();
//        HashSet<String> bookedSeats =new HashSet<>();
//        Optional<Bus> bus= busRepository.findById(busId);
//        if (bus.isPresent())
//        {
//            for (Integer i = 1; i <= bus.get().getTotalSeats(); i++) {
//                totalSeats.add(String.valueOf(i));
//            }
//        }
//        HashSet<String> concatenatedSeatList = routeRepository
//                .getSeatsBysourceDestination(origin, destination,busId,date);
//        if(!CollectionUtils.isEmpty(concatenatedSeatList))
//        {
//            concatenatedSeatList.forEach(concatenatedSeat -> {
//                if (concatenatedSeat != null)
//                    bookedSeats.addAll(List.of(concatenatedSeat.split("#")));
//            });
//        }
//        totalSeats.removeAll(bookedSeats);
//        return totalSeats;
//    }

    @Override
    public HashSet<String> getSeatsBySourceAndDestination(String origin, String destination, Integer busId,String date)
    {
        HashSet<String> totalSeats = new HashSet<>();
        HashSet<String> bookedSeats =new HashSet<>();
        HashSet<String> onHold =new HashSet<>();
        Optional<Bus> bus= busRepository.findById(busId);
        if (bus.isPresent())
        {
            for (Integer i = 1; i <= bus.get().getTotalSeats(); i++) {
                totalSeats.add(String.valueOf(i));
            }
        }
        HashSet<String> concatenatedSeatList = routeRepository
                .getSeatsBysourceDestination(origin, destination,busId,date);
        if(!CollectionUtils.isEmpty(concatenatedSeatList))
        {
            concatenatedSeatList.forEach(concatenatedSeat -> {
                if (concatenatedSeat != null)
                    bookedSeats.addAll(List.of(concatenatedSeat.split("#")));
            });
        }

        bookedSeats.forEach(bookedSeat -> {
            if(bookedSeat.contains("@"))
                onHold.add(bookedSeat.substring(1));
        });

        totalSeats.removeAll(bookedSeats);
        totalSeats.removeAll(onHold);
        return totalSeats;
    }


//    @Override
//    public boolean booksSeatsBySourceDestinationAndBusId(String origin, String destination, Integer busId, List<String> seatNos,String date)
//    {
//        Optional<Bus> bus= busRepository.findById(busId);
//        if (bus.isPresent())
//        {
//            try {
//                Bus bookedBus = bus.get();
//                final String[] newSeats = {""};
//                seatNos.forEach(seatNo -> {
//                    if (newSeats[0] != null)
//                        newSeats[0] = newSeats[0].concat("#").concat(seatNo);
//                    else
//                        newSeats[0] = seatNo;
//                });
//                List<Route> routeList = routeRepository.getRoutesBySourceDestination(origin, destination, busId,date);
//                routeList.forEach(route -> {
//                    if (route.getBookedSeats() != null)
//                        route.setBookedSeats(route.getBookedSeats().concat(newSeats[0]));
//                    else
//                        route.setBookedSeats(newSeats[0]);
//                    routeRepository.save(route);
//                });
//                return true;
//            }
//            catch (Exception e)
//            {
//                e.printStackTrace();
//                return false;
//            }
//        }
//        else
//        {
//            return false;
//        }
//    }

    @Override
    public boolean booksSeatsBySourceDestinationAndBusId(Boolean flag,String origin, String destination, Integer busId, List<String> seatNos,String date)
    {
        Optional<Bus> bus= busRepository.findById(busId);
        if (bus.isPresent())
        {
            try {
                Bus bookedBus = bus.get();
                final String[] newSeats = {""};
                if(flag==false){
                    seatNos.forEach(seatNo -> {
                        if (newSeats[0] != null)
                            newSeats[0] = newSeats[0].concat("#@").concat(seatNo);
                        else
                            newSeats[0] = seatNo;
                    });
                }else{
                    seatNos.forEach(seatNo -> {
                        if (newSeats[0] != null)
                            newSeats[0] = newSeats[0].concat("#").concat(seatNo);
                        else
                            newSeats[0] = seatNo;
                    });
                }
                List<Route> routeList = routeRepository.getRoutesBySourceDestination(origin, destination, busId,date);
                routeList.forEach(route -> {
                    if (route.getBookedSeats() != null)
                        route.setBookedSeats(route.getBookedSeats().concat(newSeats[0]));
                    else
                        route.setBookedSeats(newSeats[0]);
                    routeRepository.save(route);
                });
                return true;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    @Override
    public boolean cancelSeatsBySourceDestinationAndBusIdAndBookingId(String origin, String destination, Integer busId, String seatId,String date) {
        Optional<Bus> bus= busRepository.findById(busId);
        if(bus.isPresent()){
            List<Route> routeList=routeRepository.getRoutesBySourceDestination(origin,destination,busId,date);
            routeList.forEach(route -> {
                if(route.getBookedSeats()!=null){
                    String seats = route.getBookedSeats();
                    final String[] newSeats = {""};
                    List<String> seatList = Arrays.asList(seats.split("#"));
                    seatList.forEach(seat -> {
                        if (!seat.equalsIgnoreCase(seatId))
                        {
                            newSeats[0] = newSeats[0].concat("#").concat(seat);
                        }
                    });
                    route.setBookedSeats(newSeats[0]);
                    routeRepository.save(route);
                }
            });
            return true;
        }else {
            return false;
        }





    }

//    @Override
//    public boolean cancelReservedSeatsBySourceDestinationAndBusIdAndBookingId(String origin, String destination, Integer busId, String seatId,String date) {
//        Optional<Bus> bus= busRepository.findById(busId);
//        if(bus.isPresent()){
//            List<Route> routeList=routeRepository.getRoutesBySourceDestination(origin,destination,busId,date);
//            routeList.forEach(route -> {
//                if(route.getReservedSeats()!=null){
//                    String seats = route.getReservedSeats();
//                    final String[] newSeats = {""};
//                    List<String> seatList = Arrays.asList(seats.split("#"));
//                    seatList.forEach(seat -> {
//                        if (!seat.equalsIgnoreCase(seatId))
//                        {
//                            newSeats[0] = newSeats[0].concat("#").concat(seat);
//                        }
//                    });
//                    route.setReservedSeats(newSeats[0]);
//                    routeRepository.save(route);
//                }
//            });
//            return true;
//        }else {
//            return false;
//        }
//
//
//
//
//
//    }

    @Override
    public List<String> getLatAndLngByBusId(Integer busId, String currentTime,String date) {

        Route routeList=routeRepository.getLatAndLngByBusId(busId,currentTime,date);
        List<String> res=new ArrayList<>();

        if(routeList!=null){
            res.add(routeList.getLat());
            res.add(routeList.getLng());
        }
        else{
            Route route=routeRepository.getFirstLatAndLangByBusId(busId,date);
            res.add(route.getLat());
            res.add(route.getLng());
        }
        return res;
    }
    public List<String> dateList(String dateInput) {

        List<String> dates=new ArrayList<>();
        dates.add(dateInput);
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(formatDate.parse(dateInput));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 14; ++i) {
            c.add(Calendar.DAY_OF_MONTH, 1);
            dates.add(formatDate.format(c.getTime()));
        }
        return dates;
    }
    @Override
    public List<Route> saveAllRoutes(List<Route> routes) {
        List<Route> b=new ArrayList<>();
       for(Route route:routes)
       {
           String date= route.getDate();
           List<String> dates=dateList(date);
           RouteDTO dto=new RouteDTO();
           dto.setBusId(route.getBusId());
           dto.setDistance(route.getDistance());
           dto.setStationName(route.getStationName());
           dto.setRouteRank(route.getRouteRank());
           dto.setScheduleTime(route.getScheduleTime());
           dto.setPrice(route.getPrice());
           dto.setLat(route.getLat());
           dto.setLng(route.getLng());
           for(String d:dates)
           {
               dto.setDate(d);
               Route r=new Route();
               r.setBusId(dto.getBusId());
               r.setDistance(dto.getDistance());
               r.setStationName(dto.getStationName());
               r.setRouteRank(dto.getRouteRank());
               r.setScheduleTime(dto.getScheduleTime());
               r.setPrice(dto.getPrice());
               r.setLat(dto.getLat());
               r.setLng(dto.getLng());
               r.setDate(dto.getDate());
               routeRepository.save(r);
               b.add(r);
           }

       }
       return b;
    }

    @Override
    public List<Route> getAllRoutesByDate(String date) {
        List<Route> routes=routeRepository.getAllRoutesByDate(date);
        return routes;
    }

}

















