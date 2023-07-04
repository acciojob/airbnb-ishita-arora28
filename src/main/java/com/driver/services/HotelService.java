package com.driver.services;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repositories.HotelRepository;

public class HotelService {

    
    HotelRepository hotelRepository=new HotelRepository();

    public void addHotel(Hotel hotel){
        

         hotelRepository.addHotel(hotel);
    }

    public void addUser(User user) {
         hotelRepository.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        return hotelRepository.getHotelWithMostFacilities();
    }

    public int bookARoom(Booking booking) {
        return hotelRepository.bookARoom(booking);
    }

    public int getBookings(Integer aadharCard) {
        return hotelRepository.getBookings(aadharCard);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        return hotelRepository.updateFacilities(newFacilities,hotelName);
    }
    
}
