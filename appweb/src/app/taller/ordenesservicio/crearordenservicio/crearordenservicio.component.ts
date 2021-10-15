import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-crearordenservicio',
  templateUrl: './crearordenservicio.component.html',
  styleUrls: ['./crearordenservicio.component.css']
})
export class CrearordenservicioComponent implements OnInit {

  constructor(private servicioModal: NgbModal) { }

  ngOnInit(): void {
  }

  verDispobilidadTecnico(modal){
    this.servicioModal.open(modal);
  }
}
