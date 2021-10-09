import { NgModule } from "@angular/core";
import { CommonModule } from "@angular/common";
import { RouterModule } from "@angular/router";
import { AdminLayoutRoutes } from "../layouts/admin-layout/admin-layout.routing";
import { NgbModule } from "@ng-bootstrap/ng-bootstrap";
import { ConsultarserviciostecnicosComponent } from "./serviciostecnicos/consultarserviciostecnicos/consultarserviciostecnicos.component";
import { CrearserviciotecnicoComponent } from "./serviciostecnicos/crearserviciotecnico/crearserviciotecnico.component";
import { ConsultarcatalogocategoriasComponent } from "./serviciostecnicos/catalogocategorias/consultarcatalogo/consultarcatalogocategorias.component";
import { ConsultarpersonalComponent } from "./personaltecnico/consultarpersonal/consultarpersonal.component";
import { RegistrarpersonalComponent } from "./personaltecnico/registrarpersonal/registrarpersonal.component";
import { EditarpersonalComponent } from "./personaltecnico/editarpersonal/editarpersonal.component";
import { NuevorepuestoComponent } from "./inventariorepuestos/nuevorepuesto/nuevorepuesto.component";
import { EditarrepuestoComponent } from "./inventariorepuestos/editarrepuesto/editarrepuesto.component";
import { ConsultarcajasComponent } from "./inventariorepuestos/cajas/consultarcajas/consultarcajas.component";
import { ConsultarestantesComponent } from "./inventariorepuestos/estantes/consultarestantes/consultarestantes.component";
import { ConsultarubicacionesComponent } from "./inventariorepuestos/ubicaciones/consultarubicaciones/consultarubicaciones.component";
import { ConsultarcatalogomotivosComponent } from "./catalogosordenes/motivos/consultarcatalogomotivos/consultarcatalogomotivos.component";
import { ConsultarcatalogoprioridadesComponent } from "./catalogosordenes/prioridades/consultarcatalogoprioridades/consultarcatalogoprioridades.component";
import { ConsultainventarioComponent } from "./inventariorepuestos/consultainventario/consultainventario.component";
import { ConsultarcatalogosordenesComponent } from "./catalogosordenes/consultarcatalogosordenes/consultarcatalogosordenes.component";
import { ServiciostecnicosService } from "./service/serviciostecnicos.service";
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from "@angular/forms";
import { CategoriaservicioService } from "./service/categoriaservicio.service";
import { CrearcategoriaComponent } from './serviciostecnicos/catalogocategorias/crearcategoria/crearcategoria.component';
import { EditarserviciotecnicoComponent } from './serviciostecnicos/editarserviciotecnico/editarserviciotecnico.component';

@NgModule({
  declarations: [
    ConsultarserviciostecnicosComponent,
    CrearserviciotecnicoComponent,
    ConsultarcatalogocategoriasComponent,
    ConsultarpersonalComponent,
    RegistrarpersonalComponent,
    EditarpersonalComponent,
    NuevorepuestoComponent,
    EditarrepuestoComponent,
    ConsultarcajasComponent,
    ConsultarestantesComponent,
    ConsultarubicacionesComponent,
    ConsultarcatalogomotivosComponent,
    ConsultarcatalogoprioridadesComponent,
    ConsultainventarioComponent,
    ConsultarcatalogosordenesComponent,
    CrearcategoriaComponent,
    EditarserviciotecnicoComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    NgbModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [ServiciostecnicosService, CategoriaservicioService],
})
export class AreatecnicaModule {}
