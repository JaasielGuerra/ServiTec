import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-consultarestantes',
  templateUrl: './consultarestantes.component.html',
  styleUrls: ['./consultarestantes.component.css']
})
export class ConsultarestantesComponent implements OnInit {

  constructor(private modal: NgbModal) { }

  ngOnInit(): void {
  }

  nuevoEstante(form){
    this.modal.open(form);
  }

}
