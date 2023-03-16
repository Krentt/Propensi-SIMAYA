package com.a05.simaya.event.controller;


import com.a05.simaya.anggota.model.AnggotaModel;
import com.a05.simaya.anggota.service.AnggotaService;
import com.a05.simaya.event.model.EventModel;
<<<<<<< HEAD
=======
import com.a05.simaya.event.model.ProgresModel;
>>>>>>> a3ce889bedc3a0a67b1e8476108409bebedf0cc8
import com.a05.simaya.event.payload.CreateEventDTO;
import com.a05.simaya.event.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private AnggotaService anggotaService;

    @Autowired
    private EventService eventService;

    @GetMapping(value = "/tambah-event")
    public String getPage(Model model) {
        CreateEventDTO event = new CreateEventDTO();

        List<AnggotaModel> listAnggota = anggotaService.getListAnggota();

        model.addAttribute("listAnggota", listAnggota);
        model.addAttribute("event", event);
        return "event/form-tambah-event";
    }

    @PostMapping(value = "/tambah-event")
    public String postForm(CreateEventDTO event) {
        eventService.tambahEvent(event);
        return "hello_world";
    }

    @GetMapping(value = "/event")
    public String getDashboardEvent(Model model) {
        List<EventModel> listOngoing = eventService.getListOngoing();
        List<EventModel> listUpcoming = eventService.getListUpcoming();
        model.addAttribute("listOngoing", listOngoing);
        model.addAttribute("listUpcoming", listUpcoming);
        return "event/dashboard-event";
    }

    @GetMapping(value="/event/{id}")
    public String viewDetailEvent(
            @PathVariable(value = "id") Long idEvent,
            Model model
    ){
        EventModel event = eventService.getEventById(idEvent);
        List<ProgresModel> listProgres = event.getListProgres();
        model.addAttribute("event", event);
        model.addAttribute("listProgres", listProgres);
        return "event/detail-event";
    }

    @GetMapping(value = "/event/hapus/{id}")
    public String deleteEvent(
            @PathVariable(value = "id") Long idEvent
    ){
        eventService.deleteEvent(idEvent);
        return "hello_world";
    }
}
