import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatListModule } from '@angular/material/list';
import { HttpClientModule } from '@angular/common/http';
import { HomePageComponent } from './home-page/home-page.component';
import { CarRepairShopSelectionComponent } from './car-repair-shop-selection/car-repair-shop-selection.component';
import { FindCarRepairShopComponent } from './find-car-repair-shop/find-car-repair-shop.component';
import { RateCarRepairShopComponent } from './rate-car-repair-shop/rate-car-repair-shop.component';
import { MatStepperModule } from '@angular/material/stepper';
import { AuthGuard } from './auth-guard';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { AddCarRepairShopComponent } from './add-car-repair-shop/add-car-repair-shop.component';
import { MatDialogModule} from '@angular/material/dialog';
import {MatSliderModule} from '@angular/material/slider';
import { StarRatingComponent } from './star-rating/star-rating.component';
import {MatTableModule} from '@angular/material/table';
import { MalfunctionHistoryComponent } from './malfunction-history/malfunction-history.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    HomePageComponent,
    CarRepairShopSelectionComponent,
    FindCarRepairShopComponent,
    RateCarRepairShopComponent,
    AddCarRepairShopComponent,
    StarRatingComponent,
    MalfunctionHistoryComponent
  ],

  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatFormFieldModule,
    MatSelectModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatCardModule,
    MatButtonModule,
    HttpClientModule,
    MatListModule,
    MatStepperModule,
    MatAutocompleteModule,
    MatDialogModule,
    MatSliderModule,
    MatTableModule
  ],
  providers: [AuthGuard],
  bootstrap: [AppComponent]
})
export class AppModule { }
