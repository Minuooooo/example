package com.project.divide.service;

import com.project.divide.domain.Repeat;
import com.project.divide.domain.Role;
import com.project.divide.domain.Schedule;
import com.project.divide.domain.ScheduleStatus;
import com.project.divide.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleService {

    private final RoleRepository roleRepository;

    //지정일에 맞는 역할
    //결과가 비어있으면 checkScheduleStatus 실행x
    public List<Role> checkMyRole(LocalDate date) {

        List<Repeat> repeats = roleRepository.findAllRepeat();
        List<Role> result = new ArrayList<>();

        checkDay(date, repeats, result);

        return checkDate(date, result);
    }

    //일정 존재 여부 체크
    public ScheduleStatus checkScheduleStatus(LocalDate date) {

        List<Schedule> schedules = roleRepository.findAllSchedule();

        if (schedules.isEmpty()) {
            return ScheduleStatus.NO;
        } else {
            return checkAccessedDate(date, schedules);
        }
    }

    //반복 주기에 따른 요일이 맞는지 체크
    public void checkDay(LocalDate date, List<Repeat> repeats, List<Role> result) {
        for (Repeat repeat : repeats) {
            if (date.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.US)
                    .toUpperCase().equals(repeat.getDay())) {
                result.add(repeat.getRole());
            }
        }
    }

    //시작, 종료일 사이에 있는지 체크
    public List<Role> checkDate(LocalDate date, List<Role> result) {
        return result.stream()
                .filter(role -> role.getStartDate().compareTo(date) >= 0 && role.getEndDate().compareTo(date) <= 0)
                .collect(Collectors.toList());
    }

    //지정일 상태 조회
    private static ScheduleStatus checkAccessedDate(LocalDate date, List<Schedule> schedules) {
        Optional<Schedule> result = schedules.stream()
                .filter(schedule -> schedule.getAccessedDate().isEqual(date))
                .findAny();

        return result.orElseThrow().getScheduleStatus();
    }


}
