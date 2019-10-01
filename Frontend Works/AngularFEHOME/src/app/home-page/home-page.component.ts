import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl } from '@angular/forms';
import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Router } from '@angular/router';
import { MapsService, location } from '../maps.service';

@Component({
  selector: 'app-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.css']
})
export class HomePageComponent implements OnInit {
  searchRestaurantForm:FormGroup;
  lat:number;
  long:number;
  location:location;
  constructor(private router:Router,private map:MapsService) { }

  ngOnInit() { 
    if(sessionStorage.getItem('email')!=null&&sessionStorage.getItem('email')!=undefined){
      this.router.navigate(['foodHome']);
    }
    this.searchRestaurantForm= new FormGroup(
      {
        searchCriteria: new FormControl()
      
      }
    )
    this.map.getLocation().subscribe(data=>{
      console.log(data);
      this.location = data;
      this.lat = data.latitude;
      this.long = data.longitude;
    })
  }
  redirectToRestaurant()
  {
    console.log(this.searchRestaurantForm.get("searchCriteria").value);
    if(this.searchRestaurantForm.get("searchCriteria").value===null)
    {
      return;
    }
    console.log("inside redirect function");
    sessionStorage.setItem("criteria",this.searchRestaurantForm.get("searchCriteria").value);
    this.router.navigate(['restaurant']);
  }
}
