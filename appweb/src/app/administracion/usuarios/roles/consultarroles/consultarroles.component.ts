import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-consultarroles",
  templateUrl: "./consultarroles.component.html",
  styleUrls: ["./consultarroles.component.css"],
})
export class ConsultarrolesComponent implements OnInit {
  modalRef: NgbModalRef;

  constructor(private serviceMod: NgbModal) {}

  ngOnInit(): void {}

  nuevoRol(modal) {
    this.modalRef = this.serviceMod.open(modal, { size: "lg" });
  }
}
