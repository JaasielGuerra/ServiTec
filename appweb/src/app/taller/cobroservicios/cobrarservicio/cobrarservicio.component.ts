import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-cobrarservicio",
  templateUrl: "./cobrarservicio.component.html",
  styleUrls: ["./cobrarservicio.component.css"],
})
export class CobrarservicioComponent implements OnInit {
  modalFotosRef: NgbModalRef;
  modalRepuestosRef: NgbModalRef;
  modalServiciosRef: NgbModalRef;

  constructor(private serMd: NgbModal) {}

  ngOnInit(): void {}

  verFotosDiagnostico(modal) {
    this.modalFotosRef = this.serMd.open(modal, { size: "lg" });
  }

  verRepuestosEmpleados(modal) {
    this.modalRepuestosRef = this.serMd.open(modal, { size: "lg" });
  }

  verServiciosAplicados(modal) {
    this.modalServiciosRef = this.serMd.open(modal, { size: "lg" });
  }
}
