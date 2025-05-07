import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AggSolicitudesComponent } from './agg-solicitudes.component';

describe('AggSolicitudesComponent', () => {
  let component: AggSolicitudesComponent;
  let fixture: ComponentFixture<AggSolicitudesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AggSolicitudesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AggSolicitudesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
