package com.klef.fsad.exam.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.Date;

@Entity
public class Hotel {

    @Id
    private int hotelId;

    private String name;
    private Date date;
    private String status;
    private String location;
    private double rating;

    public Hotel() {
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }
}


package com.klef.fsad.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klef.fsad.exam.model.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, Integer> {

}

package com.klef.fsad.exam.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.klef.fsad.exam.model.Hotel;
import com.klef.fsad.exam.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    public Hotel addHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public Hotel updateHotel(int id, Hotel hotelDetails) {

        Hotel hotel = hotelRepository.findById(id).orElse(null);

        if(hotel != null) {
            hotel.setName(hotelDetails.getName());
            hotel.setDate(hotelDetails.getDate());
            hotel.setStatus(hotelDetails.getStatus());
            hotel.setLocation(hotelDetails.getLocation());
            hotel.setRating(hotelDetails.getRating());

            return hotelRepository.save(hotel);
        }

        return null;
    }
}


package com.klef.fsad.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klef.fsad.exam.model.Hotel;
import com.klef.fsad.exam.service.HotelService;

@RestController
@RequestMapping("/hotel")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    // POST - Add Hotel
    @PostMapping("/add")
    public Hotel addHotel(@RequestBody Hotel hotel) {
        return hotelService.addHotel(hotel);
    }

    // PUT - Update Hotel
    @PutMapping("/update/{id}")
    public Hotel updateHotel(@PathVariable int id, @RequestBody Hotel hotel) {
        return hotelService.updateHotel(id, hotel);
    }
}












