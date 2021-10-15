import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-consultarubicaciones',
  templateUrl: './consultarubicaciones.component.html',
  styleUrls: ['./consultarubicaciones.component.css']
})
export class ConsultarubicacionesComponent implements OnInit {

  constructor(private modal: NgbModal) { }

  ngOnInit(): void {
  }

  nuevaUbicacion(form){
    this.modal.open(form);
  }

}
