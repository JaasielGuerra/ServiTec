import { Routes } from "@angular/router";

import { DashboardComponent } from "../../dashboard/dashboard.component";

import { OrdenesservicioComponent } from "../../taller/ordenesservicio/ordenesservicio.component";
import { AtenderordenesComponent } from "../../taller/atenderordenes/atenderordenes.component";
import { CobroserviciosComponent } from "../../taller/cobroservicios/cobroservicios.component";
import { ClientesComponent } from "../../taller/clientes/clientes.component";
import { UsuariosComponent } from "../../administracion/usuarios/usuarios.component";
import { RolesComponent } from "../../administracion/roles/roles.component";
import { ReportesComponent } from "../../administracion/reportes/reportes.component";
import { ConsultarserviciostecnicosComponent } from "../../areatecnica/serviciostecnicos/consultarserviciostecnicos/consultarserviciostecnicos.component";
import { ConsultarcatalogocategoriasComponent } from "../../areatecnica/serviciostecnicos/catalogocategorias/consultarcatalogo/consultarcatalogocategorias.component";
import { CrearserviciotecnicoComponent } from "../../areatecnica/serviciostecnicos/crearserviciotecnico/crearserviciotecnico.component";
import { ConsultarpersonalComponent } from "../../areatecnica/personaltecnico/consultarpersonal/consultarpersonal.component";
import { ConsultarcatalogosordenesComponent } from "../../areatecnica/catalogosordenes/consultarcatalogosordenes/consultarcatalogosordenes.component";
import { ConsultainventarioComponent } from "../../areatecnica/inventariorepuestos/consultainventario/consultainventario.component";
import { EditarserviciotecnicoComponent } from "../../areatecnica/serviciostecnicos/editarserviciotecnico/editarserviciotecnico.component";
import { Status404Component } from "../status404/status404.component";

/**
 * Configuracion de rutas para navegacion de la aplicacion del menu principal
 */
export const AdminLayoutRoutes: Routes = [
  { path: "notfound", component: Status404Component },
  { path: "dashboard", component: DashboardComponent },
  { path: "ordenes-servicio", component: OrdenesservicioComponent },
  { path: "atender-ordenes", component: AtenderordenesComponent },
  { path: "cobro-servicios", component: CobroserviciosComponent },
  { path: "clientes", component: ClientesComponent },
  { path: "servicios-tecnicos", component: ConsultarserviciostecnicosComponent },
  { path: 'servicios-tecnicos/categorias', component: ConsultarcatalogocategoriasComponent },
  { path: 'servicios-tecnicos/nuevo', component: CrearserviciotecnicoComponent },
  { path: 'servicios-tecnicos/editar/:id', component: EditarserviciotecnicoComponent },
  { path: "personal-tecnico", component: ConsultarpersonalComponent },
  { path: "catalogo-ordenes", component: ConsultarcatalogosordenesComponent },
  { path: "inventario-repuestos", component: ConsultainventarioComponent },
  { path: "usuarios", component: UsuariosComponent },
  { path: "reportes", component: ReportesComponent },
];
