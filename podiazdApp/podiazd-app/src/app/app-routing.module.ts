import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthGuard } from './auth-guard';
import { CarRepairShopSelectionComponent } from './car-repair-shop-selection/car-repair-shop-selection.component';
import { FindCarRepairShopComponent } from './find-car-repair-shop/find-car-repair-shop.component';
import { HomePageComponent } from './home-page/home-page.component';
import { LoginComponent } from './login/login.component';
import { RateCarRepairShopComponent } from './rate-car-repair-shop/rate-car-repair-shop.component';
import { AddCarRepairShopComponent } from './add-car-repair-shop/add-car-repair-shop.component';
import { RegistrationComponent } from './registration/registration.component';
import { MalfunctionHistoryComponent } from './malfunction-history/malfunction-history.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'registration', component: RegistrationComponent },
  { path: 'home-page', component: HomePageComponent, canActivate: [AuthGuard] },
  { path: 'car-repair-shop-selection', component: CarRepairShopSelectionComponent, canActivate: [AuthGuard] },
  { path: 'find-car-repair-shop', component: FindCarRepairShopComponent, canActivate: [AuthGuard] },
  { path: 'rate-car-repair-shop', component: RateCarRepairShopComponent, canActivate: [AuthGuard] },
  { path: 'add-car-repair-shop', component: AddCarRepairShopComponent, canActivate: [AuthGuard] },
  { path: 'malfunction-history', component: MalfunctionHistoryComponent, canActivate: [AuthGuard] },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
