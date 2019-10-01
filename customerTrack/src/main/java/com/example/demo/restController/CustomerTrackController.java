package com.example.demo.restController;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.entity.CustomerAccount;
import com.example.demo.entity.CustomerTrack;
import com.example.demo.entity.CustomerTrackDto;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.CustomerTrackRepository;
import com.example.demo.requestAndResponseModel.RequestModel;
import com.example.demo.requestAndResponseModel.ResponseModel;
import com.example.demo.service.CustomerTrackService;


@RestController
public class CustomerTrackController {  
	@Autowired
	private CustomerTrackRepository customerTrackRepository;
	private CustomerTrackService customerTrackService;
	private CustomerRepository customerRepository;
	private ModelMapper mapper;
	   @Autowired
		public CustomerTrackController(CustomerTrackService customerTrackService,CustomerRepository customerRepository) {
			super();
			this.customerTrackService = customerTrackService;
			this.customerRepository = customerRepository;
			this.mapper = new ModelMapper();
			this.mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
			
		}
	   
	   @PostMapping("/customerTrack/{cUuid}")
	   public ResponseEntity<ResponseModel> insertPhysicalDetail(@RequestBody RequestModel requestModel,@PathVariable("cUuid") String cuuid)
	   {    
		   Optional<CustomerAccount> customerAccount = customerRepository.findByUid(cuuid);
		   CustomerTrackDto customerTrackDto = new CustomerTrackDto(requestModel.getTackingDate(), requestModel.getMealTime(),requestModel.getCalories());
		      customerTrackDto.setCustomerAccount(customerAccount.get());
		      customerTrackDto = customerTrackService.insertcustomeTrackDto(customerTrackDto);
		   ResponseModel responseModel = mapper.map(customerTrackDto, ResponseModel.class);
		   
		   return ResponseEntity.ok(responseModel);
	   }
	   @GetMapping("/customerTrack/{uuid}")
	   public ResponseEntity<ResponseModel> findByUUID(@PathVariable("uuid") String uuid)
	   {
		   CustomerTrackDto customerTrackDto = customerTrackService.findByUUID(uuid);
		   if(customerTrackDto==null)
		   {
			   return (ResponseEntity<ResponseModel>) ResponseEntity.notFound();
		   }
		   else
		   {
			   ResponseModel responseModel = mapper.map(customerTrackDto, ResponseModel.class);
			   return ResponseEntity.ok(responseModel);
		   }
	   }
	   @GetMapping("/customerTrack/byCustomer/{cuuid}")
	   public ResponseEntity<ResponseModel> findCustomerTrackByCustomer(@PathVariable("cuuid") String s)
	   {
		   
		   Optional<CustomerAccount> tempCustomer = customerRepository.findByUid(s);
		   Optional<CustomerTrack> cDetail = customerTrackRepository.findByCustomerAccount(tempCustomer.get());
		   ResponseModel responseModel = mapper.map(cDetail.get(), ResponseModel.class);
		   return ResponseEntity.ok(responseModel);
		   
	   }
	   @PutMapping("/customerTrack/{uuid}")
	   public ResponseEntity<ResponseModel> customerTrack(@RequestBody RequestModel requestModel,@PathVariable("uuid") String uuid)
	   {
		   CustomerTrackDto customerTrackDto = new CustomerTrackDto(requestModel.getTackingDate(), requestModel.getMealTime(),requestModel.getCalories());
		   customerTrackDto.setUtUuid(uuid);
		   customerTrackDto=customerTrackService.updateCustomerTrack(customerTrackDto);
		   ResponseModel responseModel = mapper.map(customerTrackDto, ResponseModel.class);
		   return ResponseEntity.ok(responseModel);
	   }
	   
	   @DeleteMapping("/physicalDetails/{uuid}")
	   public ResponseEntity<String> deleteByUUID(@PathVariable("uuid") String uuid)
	   {
		  customerTrackService.deleteByUUID(uuid);
		  
		  return ResponseEntity.ok("Deleted");
	   }
		

}
