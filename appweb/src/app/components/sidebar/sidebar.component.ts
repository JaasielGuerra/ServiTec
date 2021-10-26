import { Component, OnInit } from '@angular/core';

declare interface RouteInfo {
    path: string;
    title: string;
    icon: string;
    class: string;
}
export const ROUTES: RouteInfo[] = [
    { path: '/dashboard', title: 'Dashboard',  icon: 'design_app', class: '' },

    // taller
    { path: '/ordenes-servicio', title: 'Ordenes servicio',  icon:'files_paper', class: '' },
    { path: '/atender-ordenes', title: 'Atender ordenes',  icon:'ui-2_settings-90', class: '' },
    { path: '/cobro-servicios', title: 'Cobro servicios',  icon:'business_money-coins', class: '' },
    { path: '/clientes', title: 'Clientes',  icon:'users_circle-08', class: '' },

    //area tecnica
    { path: '/servicios-tecnicos', title: 'Servicios tecnicos',  icon:'design_bullet-list-67', class: '' },
    { path: '/personal-tecnico', title: 'Personal tecnico',  icon:'business_badge', class: '' },
    { path: '/inventario-repuestos', title: 'Inventario repuestos',  icon:'files_box', class: '' },

    //administracion
    { path: '/usuarios', title: 'Usuarios',  icon:'users_single-02', class: '' },
    { path: '/reportes', title: 'Reportes',  icon:'business_chart-bar-32', class: '' }

];

@Component({
  selector: 'app-sidebar',
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor() { }

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
  }
  isMobileMenu() {
      if ( window.innerWidth > 991) {
          return false;
      }
      return true;
  };
}
