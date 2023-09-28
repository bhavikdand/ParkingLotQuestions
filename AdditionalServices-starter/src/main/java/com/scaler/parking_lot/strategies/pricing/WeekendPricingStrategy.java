package com.scaler.parking_lot.strategies.pricing;

import com.scaler.parking_lot.models.Slab;
import com.scaler.parking_lot.models.VehicleType;
import com.scaler.parking_lot.respositories.SlabRepository;
import com.scaler.parking_lot.utils.DateTimeUtils;

import java.util.Date;
import java.util.List;

public class WeekendPricingStrategy implements PricingStrategy{

    private SlabRepository slabRepository;

    public WeekendPricingStrategy(SlabRepository slabRepository) {
        this.slabRepository = slabRepository;
    }

    @Override
    public double calculateAmount(Date entryTime, Date exitTime, VehicleType vehicleType) {

    }
}
