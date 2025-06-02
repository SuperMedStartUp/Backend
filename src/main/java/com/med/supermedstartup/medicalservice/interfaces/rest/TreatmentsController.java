package com.med.supermedstartup.medicalservice.interfaces.rest;

import com.med.supermedstartup.medicalservice.domain.model.commands.DeleteTreatmentCommand;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetAllTreatmentsQuery;
import com.med.supermedstartup.medicalservice.domain.model.queries.GetTreatmentByPatientIdQuery;
import com.med.supermedstartup.medicalservice.domain.services.TreatmentCommandService;
import com.med.supermedstartup.medicalservice.domain.services.TreatmentQueryService;
import com.med.supermedstartup.medicalservice.interfaces.rest.resources.CreateTreatmentResource;
import com.med.supermedstartup.medicalservice.interfaces.rest.resources.TreatmentResource;
import com.med.supermedstartup.medicalservice.interfaces.rest.transform.CreateTreatmentCommandFromResourceAssembler;
import com.med.supermedstartup.medicalservice.interfaces.rest.transform.TreatmentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/treatments", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name="Treatments", description = "Treatment Management Endpoints")
public class TreatmentsController {
    private final TreatmentCommandService treatmentCommandService;
    private final TreatmentQueryService treatmentQueryService;

    public TreatmentsController(TreatmentCommandService treatmentCommandService, TreatmentQueryService treatmentQueryService) {
        this.treatmentCommandService = treatmentCommandService;
        this.treatmentQueryService = treatmentQueryService;
    }

    @PostMapping
    public ResponseEntity<TreatmentResource> createdTreatment(@RequestBody CreateTreatmentResource resource){
        var createTreatmentCommand = CreateTreatmentCommandFromResourceAssembler.toCommandFromResource(resource);
        var treatment = treatmentCommandService.handle(createTreatmentCommand);
        if (treatment.isEmpty())
            return ResponseEntity.badRequest().build();
        var treatmentResource = TreatmentResourceFromEntityAssembler.toResourceFromEntity(treatment.get());
        return new ResponseEntity<>(treatmentResource, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TreatmentResource>> getAllTreatments(){
        var getAllTreatmentsQuery = new GetAllTreatmentsQuery();
        var treatments = treatmentQueryService.handle(getAllTreatmentsQuery);
        var treatmentResources = treatments.stream().map(TreatmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(treatmentResources);
    }

    @GetMapping("patientId/{patientId}")
    public ResponseEntity<List<TreatmentResource>> getTreatmentByPatientId(@PathVariable Long patientId){
        var getTreatmentByPatientIdQuery = new GetTreatmentByPatientIdQuery(patientId);
        var treatments = treatmentQueryService.handle(getTreatmentByPatientIdQuery);
        if (treatments.isEmpty())
            return ResponseEntity.notFound().build();
        var treatmentResource = treatments.stream()
                .map(TreatmentResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(treatmentResource);
    }

    @DeleteMapping("treatmentName/{treatmentName}")
    public ResponseEntity<?> deleteTreatment(@PathVariable String treatmentName){
        var deleteTreatmentCommand = new DeleteTreatmentCommand(treatmentName);
        treatmentCommandService.handle(deleteTreatmentCommand);
        return ResponseEntity.ok("Treatment with given id successfully deleted");
    }
}
