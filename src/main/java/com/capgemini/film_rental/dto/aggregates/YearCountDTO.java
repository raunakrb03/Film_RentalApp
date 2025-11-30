
package com.capgemini.film_rental.dto.aggregates; public class YearCountDTO { private Integer year; private Long count; public YearCountDTO(Integer y, Long c){this.year=y; this.count=c;} public Integer getYear(){return year;} public Long getCount(){return count;} }
