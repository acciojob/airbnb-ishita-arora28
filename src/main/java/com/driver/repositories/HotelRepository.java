package com.driver.repositories;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

public class HotelRepository {

    Map<String,Hotel> hotelMap=new HashMap<>();
    Map<Integer,User> userMap=new HashMap<>();
    Map<String,Booking> bookingMap=new HashMap<>();

    public void addHotel(Hotel hotel) throws RuntimeException {
        if(hotel==null|| hotel.getHotelName()==null || hotelMap.containsKey(hotel.getHotelName())){
            throw new RuntimeException("FAILURE");
        }
         hotelMap.put(hotel.getHotelName(),hotel);
    }
    public void addUser(User user) {
        userMap.put(user.getaadharCardNo(),user);

    }
    public String getHotelWithMostFacilities() {
        int maxFacilities=0;
        int listOfFacilites=0;
        for(String hotelName: hotelMap.keySet()){
             listOfFacilites=hotelMap.get(hotelName).getFacilities().size();
            if(listOfFacilites==0){
                return "";
            }
            if(listOfFacilites>maxFacilities){
                maxFacilities=listOfFacilites;

            }
        }
        List<String>list=new ArrayList<>();
        for(Hotel hotel:hotelMap.values()){
            if(hotel.getFacilities().size()==maxFacilities){
                list.add(hotel.getHotelName());
            }
        }
        Collections.sort(list);
        return list.get(0);

    
    }
    public int bookARoom(Booking booking) {
        Hotel hotel=hotelMap.get(booking.getHotelName());
        if(hotel.getAvailableRooms()<booking.getNoOfRooms()){
            return -1;
        }
        String bookingId=UUID.randomUUID().toString();
        booking.setBookingId(bookingId);
        booking.setAmountToBePaid(booking.getNoOfRooms()*hotel.getPricePerNight());
        bookingMap.put(bookingId,booking);
        return booking.getAmountToBePaid();

    }
    public int getBookings(Integer aadharCard) {
        int count=0;
        for(Booking booking: bookingMap.values()){
            if(booking.getBookingAadharCard()==aadharCard)
                count++;
        }
        return count;
    }
    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        Hotel hotel=hotelMap.get(hotelName);
        List<Facility> currentFacilities=hotel.getFacilities();
        Set<Facility> currFacilitySet=new HashSet<>(currentFacilities);
        for(Facility newfacility:newFacilities){
            if(!currFacilitySet.contains(newfacility)){
                currFacilitySet.add(newfacility);
            }
        }
        hotel.setFacilities(newFacilities);
        return hotel;
    }
}
    