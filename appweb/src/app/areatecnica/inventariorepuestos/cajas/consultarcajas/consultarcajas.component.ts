import { Component, OnInit } from '@angular/core';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-consultarcajas',
  templateUrl: './consultarcajas.component.html',
  styleUrls: ['./consultarcajas.component.css']
})
export class ConsultarcajasComponent implements OnInit {

  constructor(private modal: NgbModal) { }

  ngOnInit(): void {
  }

  nuevaCaja(form){
    this.modal.open(form);
  }
}
