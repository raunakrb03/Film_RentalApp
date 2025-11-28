package com.capgemini.film_rental.entity.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RatingConverter implements AttributeConverter<Rating, String>
{

    @Override
    public String convertToDatabaseColumn(Rating rating) {
        if (rating == null) {
            return null;
        }
        switch (rating) {
            case G:
                return "G";
            case PG:
                return "PG";
            case PG_13:
                return "PG-13";
            case R:
                return "R";
            case NC_17:
                return "NC-17";
            default:
                throw new IllegalArgumentException("Unknown rating: " + rating);
        }
    }

    @Override
    public Rating convertToEntityAttribute(String dbData)
    {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        switch (dbData) {
            case "G":
                return Rating.G;
            case "PG":
                return Rating.PG;
            case "PG-13":
                return Rating.PG_13;
            case "R":
                return Rating.R;
            case "NC-17":
                return Rating.NC_17;
            default:
                throw new IllegalArgumentException("Unknown rating from database: " + dbData);
        }
    }
}