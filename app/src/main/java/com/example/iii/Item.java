package com.example.iii;

public class Item {

    private String region;
    private Long activeCases;
    private Long newInfected;
    private Long recovered;
    private Long newRecovered;
    private Long deceased;
    private Long newDeceased;
    private Long totalInfected;

    public Item (String region, Long activeCases, Long newInfected, Long recovered, Long newRecovered,
                 Long deceased, Long newDeceased, Long totalInfected) {
        this.region = region;
        this.activeCases = activeCases;
        this.newInfected = newInfected;
        this.recovered = recovered;
        this.newRecovered = newRecovered;
        this.deceased = deceased;
        this.newDeceased = newDeceased;
        this.totalInfected = totalInfected;
    }

    public String getRegion () {
        return region;
    }

    public Long getActiveCases () {
        return activeCases;
    }

    public Long getNewInfected () {
        return newInfected;
    }

    public Long getRecovered () {
        return recovered;
    }

    public Long getNewRecovered () {
        return newRecovered;
    }

    public Long getDeceased () {
        return  deceased;
    }

    public Long getNewDeceased () {
        return newDeceased;
    }

    public Long getTotalInfected () {
        return totalInfected;
    }

}
