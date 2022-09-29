package com.springboot.mybus.Service;

import com.springboot.mybus.model.Bus;
import com.springboot.mybus.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BusServiceImpl implements  BusService{

    @Autowired
    private BusRepository busReopsitory;
//
//    @Override
//    public List<Bus> findAll() {
//        return busReopsitory.findAll();
//    }
//
//    @Override
//    public Bus save(Bus bus) {
//        return busReopsitory.save(bus);
//    }
//
//    @Override
//    public Optional<Bus> findById(Integer theId) {
//        return busReopsitory.findById(theId);
//    }
//
//    @Override
//    public void deleteById(Integer theId) {
//         busReopsitory.deleteById(theId);
//    }

    public Bus updateBus(Bus bus) {

        Bus b=busReopsitory.findById(bus.getBusId()).orElse(null);
        b.setBusNumber(bus.getBusNumber());
        b.setBusName(bus.getBusName());
        b.setBusType(bus.getBusType());
        b.setTotalSeats(bus.getTotalSeats());

        return busReopsitory.save(b);
//        Bus b=  busRepository.findById(bus.getId()).orElse(null);
//        b.setRatings(bus.getRatings());
//        b.setBusName(bus.getBusName());
//        b.setCategory(bus.getCategory());
//        b.setBusNo(bus.getBusNo());
//        b.setTotalSeats(bus.getTotalSeats());
//        b.setBusRoutes(bus.getBusRoutes());
//        return  busRepository.save(b);
    }

    @Scheduled(fixedDelay = 5 * 60 * 1000)
    public void clearOnHoldSeats()
    {
        System.out.println("In scheduler");
        List<Bus> allBuses = busReopsitory.findAll();
        List<Bus> modifiedBuses = new ArrayList<>();
        allBuses.forEach(bus -> {
            bus.getRouteList().forEach(route -> {
                boolean isCleared = false;
                if (route.getBookedSeats() != null
                        && !route.getBookedSeats().isEmpty()
                        && route.getBookedSeats().contains("@"))
                {
                    isCleared = true;
                    String[] seats = route.getBookedSeats().split("#");
                    String bookedSeats = "";
                    for (String seat : seats) {
                        if (!seat.contains("@"))
                        {
                            if (bookedSeats.isEmpty())
                                bookedSeats = seat;
                            else
                                bookedSeats = bookedSeats.concat("#").concat(seat);
                        }
                    }
                    route.setBookedSeats(bookedSeats);
                }
                if (isCleared)
                    modifiedBuses.add(bus);
            });
        });
        busReopsitory.saveAll(modifiedBuses);
    }

}
