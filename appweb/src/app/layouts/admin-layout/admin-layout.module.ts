import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AdminLayoutRoutes } from './admin-layout.routing';
import { ChartsModule } from 'ng2-charts';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ToastrModule } from 'ngx-toastr';

import { DashboardComponent } from '../../dashboard/dashboard.component';
import { Status404Component } from '../status404/status404.component';
import { TallerModule } from '../../taller/taller.module';
import { AdministracionModule } from '../../administracion/administracion.module';
import { AreatecnicaModule } from '../../areatecnica/areatecnica.module';

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AdminLayoutRoutes),
    FormsModule,
    ChartsModule,
    NgbModule,
    AreatecnicaModule,
    TallerModule,
    AdministracionModule
  ],
  declarations: [
    DashboardComponent,
    Status404Component
  ]
})

export class AdminLayoutModule {}
