import { Component, OnInit } from "@angular/core";
import { NgbModal, NgbModalRef } from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: "app-catalogomotivos",
  templateUrl: "./catalogomotivos.component.html",
  styleUrls: ["./catalogomotivos.component.css"],
})
export class CatalogomotivosComponent implements OnInit {
  modalRef: NgbModalRef;

  constructor(private serviceMod: NgbModal) {}

  ngOnInit(): void {}

  nuevoMotivo(modal) {
    this.modalRef = this.serviceMod.open(modal);
  }

  cerrar(){
    this.modalRef.close();
  }
}
