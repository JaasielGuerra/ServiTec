import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { ReportesComponent } from "./reportes/reportes.component";
import { ConsultarusuariosComponent } from "./usuarios/consultarusuarios/consultarusuarios.component";
import { CrearusuarioComponent } from "./usuarios/crearusuario/crearusuario.component";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { FormsModule } from "@angular/forms";
import { ConsultarrolesComponent } from "./usuarios/roles/consultarroles/consultarroles.component";
import { RouterModule } from "@angular/router";
import { AdminLayoutRoutes } from "../layouts/admin-layout/admin-layout.routing";
import { UsuariosService } from "./service/usuarios.service";
import { HttpModule } from "@angular/http";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [
    ReportesComponent,
    ConsultarusuariosComponent,
    CrearusuarioComponent,
    ConsultarrolesComponent,
  ],
  imports: [
    CommonModule,
    NgbModule,
    FormsModule,
    RouterModule.forChild(AdminLayoutRoutes),
    HttpClientModule
  ],
  providers: [UsuariosService],
})
export class AdministracionModule {}
