import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-atenderorden",
  templateUrl: "./atenderorden.component.html",
  styleUrls: ["./atenderorden.component.css"],
})
export class AtenderordenComponent implements OnInit {
  modalClienteRef: NgbModalRef;
  modalFotoRef: NgbModalRef;
  modalServicioRef: NgbModalRef;
  modalRepuestoRef: NgbModalRef;

  constructor(private serMd: NgbModal) {}

  ngOnInit(): void {}

  detallesCliente(modal) {
    this.modalClienteRef = this.serMd.open(modal, { size: "sm" });
  }

  agregarFoto(modal) {
    this.modalFotoRef = this.serMd.open(modal);
  }

  agregarServicio(modal){
    this.modalServicioRef = this.serMd.open(modal);
  }

  agregarRepuesto(modal){
    this.modalRepuestoRef = this.serMd.open(modal);
  }
}
