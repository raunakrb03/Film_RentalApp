package com.capgemini.film_rental.mapper;

import com.capgemini.film_rental.dto.ActorCreateDTO;
import com.capgemini.film_rental.dto.ActorDTO;
import com.capgemini.film_rental.entity.Actor;

public final class ActorMapper {

    private ActorMapper() {}

    public static Actor toEntity(ActorCreateDTO dto) {
        if (dto == null) return null;
        Actor a = new Actor();
        a.setFirstName(dto.getFirstName());
        a.setLastName(dto.getLastName());
        return a;
    }

    public static ActorDTO toDTO(Actor a) {
        if (a == null) return null;
        ActorDTO d = new ActorDTO();
        d.setActorId(a.getActorId());
        d.setFirstName(a.getFirstName());
        d.setLastName(a.getLastName());
        return d;
    }
}

