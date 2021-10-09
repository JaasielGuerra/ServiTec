import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuariosComponent } from './usuarios/usuarios.component';
import { RolesComponent } from './roles/roles.component';
import { ReportesComponent } from './reportes/reportes.component';



@NgModule({
  declarations: [UsuariosComponent, RolesComponent, ReportesComponent],
  imports: [
    CommonModule
  ]
})
export class AdministracionModule { }
