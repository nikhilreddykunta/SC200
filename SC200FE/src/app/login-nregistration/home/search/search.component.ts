import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { SearchService } from '../search.service';
import { Subject, from } from 'rxjs';
import{Router} from '@angular/router';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {
  challenges;
  searchText: string;
  showDropDown =false;
  results =false;
  result;
  @Output() datafromchild = new EventEmitter<string>();
  constructor(private searchService: SearchService,private router: Router) { }

  
  search($event) {
    let q = $event.target.value;
    this.searchText =q;
    this.showDropDown =true;
    this.results =false;
    
    if(this.searchText){

  this.searchService.findByText(this.searchText)
  .subscribe(
    data => {
    console.log("data in service",data);
      this.challenges = data;
    
});
}
}
closeDropDown(){
  this.showDropDown =false;
}
openDropDown(){
  this.showDropDown =true;
  this.results =false;
  this.searchService.findAll()
  .subscribe(
    data => {
    console.log("data in service",data);
      this.challenges = data;
    
});
}
submit($event){
    this.showDropDown =false;
      
    this.results =true;
        this.datafromchild.emit(this.searchText);
        // console.log("ffhgjhj");
}
select(id: string){
  this.router.navigate(['attempt',id]);
}
ngOnInit() {
  this.showDropDown =false;
  this.results =false;
  
}
}
