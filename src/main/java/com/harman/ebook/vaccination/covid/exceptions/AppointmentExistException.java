package com.harman.ebook.vaccination.covid.exceptions;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class AppointmentExistException extends RuntimeException{
	String message;
}
