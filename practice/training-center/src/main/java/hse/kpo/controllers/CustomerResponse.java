package hse.kpo.controllers;

public record CustomerResponse (
        int customerId,
        String customerName,
        int handPower,
        int legPower,
        int iq
) {}
