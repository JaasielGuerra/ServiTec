import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { ToastrService } from "ngx-toastr";
import { Usuario } from "../../../administracion/model/Usuario";
import { UsuariosService } from "../../../administracion/service/usuarios.service";
import { Tecnico } from "../../model/Tecnico";
import { TecnicosService } from "../../service/tecnicos.service";

@Component({
  selector: "app-editarpersonal",
  templateUrl: "./editarpersonal.component.html",
  styleUrls: ["./editarpersonal.component.css"],
})
export class EditarpersonalComponent implements OnInit {
  usuarios: Usuario[];
  tecnico: Tecnico = new Tecnico();
  idUsuarioSel: string;

  constructor(
    private userService: UsuariosService,
    private rutas: Router,
    private toastr: ToastrService,
    private service: TecnicosService,
    private activatedRoute: ActivatedRoute
  ) {}

  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.params.id;

    this.service.obtener(id.toString()).subscribe(
      (data) => {
        this.tecnico = data;
        this.idUsuarioSel = this.tecnico.usuario.idUsuario.toString();
      },
      (err) => {
        console.log(err.message);

        this.rutas.navigate(["/notfound"]);
      }
    );

    this.userService.listar("1").subscribe(
      (d) => {
        this.usuarios = d;
      },
      (e) => {
        alert("error al consultar usuarios: " + e.message);
      }
    );
  }

  guardar() {
    this.service.actualizar(this.tecnico, this.idUsuarioSel).subscribe(
      (d) => {
        this.rutas.navigate(["personal-tecnico"]);
        this.msjSuccess();
      },
      (e) => {
        this.msjError(e);
      }
    );
  }

  msjSuccess() {
    this.toastr.success(
      '<span class="now-ui-icons ui-1_bell-53"></span> Actualizado con éxito',
      "Éxito!",
      {
        closeButton: true,
        enableHtml: true,
        toastClass: "alert alert-success alert-with-icon",
        positionClass: "toast-top-right",
      }
    );
  }

  msjError(error) {
    this.toastr.error(
      '<span class="now-ui-icons ui-1_bell-53"></span> Descripción: ' +
        error.message,
      "Error",
      {
        closeButton: true,
        enableHtml: true,
        toastClass: "alert alert-danger alert-with-icon",
        positionClass: "toast-top-right",
      }
    );
  }
}
