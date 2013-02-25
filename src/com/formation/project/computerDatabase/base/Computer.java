package com.formation.project.computerDatabase.base;

import java.util.Date;


public class Computer {

    public Long id;
    
    public String name;
    
    public Date introduced;
    
    public Date discontinued;
    
    public Company company;
    
    /**
     * Generic query helper for entity Computer with id Long
     */
//    public static Finder<Long,Computer> find = new Finder<Long,Computer>(Long.class, Computer.class); 
//    
//    /**
//     * Return a page of computer
//     *
//     * @param page Page to display
//     * @param pageSize Number of computers per page
//     * @param sortBy Computer property used for sorting
//     * @param order Sort order (either or asc or desc)
//     * @param filter Filter applied on the name column
//     */
//    public static Page<Computer> page(int page, int pageSize, String sortBy, String order, String filter) {
//        return 
//            find.where()
//                .ilike("name", "%" + filter + "%")
//                .orderBy(sortBy + " " + order)
//                .fetch("company")
//                .findPagingList(pageSize)
//                .getPage(page);
//    }
    
}