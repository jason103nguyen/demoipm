package com.phuongnt.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.phuongnt.dao.EntryTestDao;
import com.phuongnt.dto.EntryTestDto;
import com.phuongnt.entities.EntryTest;
import com.phuongnt.service.EntryTestService;

@Service
@Transactional
public class EntryTestServiceImpl implements EntryTestService {

	@Autowired
	private EntryTestDao entryTestDao;
	
	@Override
	public void create(EntryTestDto entryTestDto) {

		EntryTest entryTest = new EntryTest(entryTestDto);
		entryTestDao.save(entryTest);
	}

	@Override
	public EntryTestDto readById(int id) throws Exception {

		Optional<EntryTest> entryTest = entryTestDao.findById(id);
		EntryTestDto entryTestDto = null;
		if (entryTest.isEmpty()) {
			throw new Exception("The id doesn't exists");
		} else {
			entryTestDto = new EntryTestDto(entryTest.get());
		}
		return entryTestDto;
	}

	@Override
	public List<EntryTestDto> readAll() throws Exception {

		List<EntryTest> listEntryTest = (List<EntryTest>) entryTestDao.findAll();
		List<EntryTestDto> listEntryTestDto = new ArrayList<EntryTestDto>();
		
		if (listEntryTest.isEmpty()) {
			
			throw new Exception("This list is empty");
		} else {
			
			for (EntryTest entryTest : listEntryTest) {
				EntryTestDto entryTestDto = new EntryTestDto(entryTest);
				listEntryTestDto.add(entryTestDto);
			}
		}
		
		return listEntryTestDto;
	}

	@Override
	public void update(EntryTestDto entryTestDto) {

		EntryTest entryTest = new EntryTest(entryTestDto);
		entryTestDao.save(entryTest);
	}

	@Override
	public void deleteById(int id) {

		if (entryTestDao.existsById(id)) {
			entryTestDao.deleteById(id);
		}
	}

}
