package com.demoipm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.demoipm.dto.recruitmentmanage.CareerSelectionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demoipm.dao.CareerDao;
import com.demoipm.dto.CareerDto;
import com.demoipm.entities.Career;
import com.demoipm.service.CareerService;

@Service
@Transactional
public class CareerServiceImpl implements CareerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CareerService.class);

	@Autowired
	private CareerDao careerDao;
	
	@Override
	public void create(CareerDto careerDto) {

		Career career = new Career(careerDto);
		careerDao.save(career);
	}

	@Override
	public CareerDto readById(int id) throws Exception {

		Optional<Career> career = careerDao.findById(id);
		CareerDto careerDto = null;
		if (!career.isPresent()) {
			throw new Exception("The id doesn't exists");
		} else {
			careerDto = new CareerDto(career.get());
		}
		return careerDto;
	}

	@Override
	public List<CareerDto> readAll() throws Exception {

		List<Career> listCareer = (List<Career>) careerDao.findAll();
		List<CareerDto> listCareerDto = new ArrayList<CareerDto>();
		
		if (listCareer.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (Career career : listCareer) {
				CareerDto careerDto = new CareerDto(career);
				listCareerDto.add(careerDto);
			}
		}
		
		return listCareerDto;
	}

	@Override
	public void update(CareerDto careerDto) {

		Career career = new Career(careerDto);
		careerDao.save(career);
	}

	@Override
	public void deleteById(int id) {

		if (careerDao.existsById(id)) {
			careerDao.deleteById(id);
		}
	}

	@Override
	public List<CareerSelectionDto> getAllCareer() {
		LOGGER.info("Start get all career");
		try {
			List<Career> careerEntities = careerDao.findAll();
			List<CareerSelectionDto> careerDtos = careerEntities.stream()
					.map(career ->
							new CareerSelectionDto()
									.setId(career.getId())
									.setCareer(career.getName()))
					.collect(Collectors.toList());
			return careerDtos;
		} catch (Throwable t) {
			LOGGER.error("Has error when getAllCareer", t);
			return new ArrayList<>();
		}
	}

}
