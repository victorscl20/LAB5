package com.pucp.lab5gtics.controller;

import com.pucp.lab5gtics.entity.EmployeesEntity;
import com.pucp.lab5gtics.repository.DepartmentRepository;
import com.pucp.lab5gtics.repository.EmployeeRepository;
import com.pucp.lab5gtics.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JobRepository jobRepository;

    @Autowired
    JobRepository jobRepository;

    @GetMapping({"lista", ""})
    public String listEmployee(Model model,
                               @RequestParam(name = "search",required = false) String search,
                               @RequestParam(name = "order", required = false) Integer order,
                               RedirectAttributes attributes){

        model.addAttribute("listaEmployee",employeeRepository.findAll());

        return "employee/list";
    }


    //Buscar Empleado
    public String searchEmployee(Model model, @RequestParam(name = "search",required = false) String search, @RequestParam(name = "order", required = false) Integer order, RedirectAttributes attributes){

        return "XXXXXX";
    }


    //Editar Empleado
    //@...Mapping("")
    @GetMapping("/edit")
    public String informEmployee( Model model, @RequestParam("id") int id ) {
        Optional<EmployeesEntity> optional = employeeRepository.findById(id);
        if (optional.isPresent()) {
            model.addAttribute("employee", optional.get());
            model.addAttribute("listadepartament", departmentRepository.findAll());
            model.addAttribute("listaTrabajo",jobRepository.findAll());

            return "employee/information";
        } else {
            return "redirect:/employee";
        }
    }

    //Guardar Empleado
    //@...Mapping("")
    @PostMapping("guardar")
    public String saveEmployee(EmployeesEntity employeesEntity) {
        employeeRepository.save(employeesEntity);
        return "redirect:/employee";
    }
    @GetMapping("borrar")
    public String deleteEmployee(RedirectAttributes attr,Model model, @RequestParam("id") Integer id){
        try{
        employeeRepository.deleteById(id);
        attr.addFlashAttribute("msgExitoso","El empleado ha sido eliminado");
        }
        catch (Exception e){
            attr.addFlashAttribute("msgError","Este empleado no puede ser eliminado");
        }
        return "redirect:/employee";
    }

    //Nuevo Empleado
    @GetMapping("nuevo")
    public String newEmployee(Model model) {
        model.addAttribute("listaTrabajos",jobRepository.findAll());
        return "employee/nuevo";
    }
}
