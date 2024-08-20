package.com.example.demo.controlador;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ErrorController {

    @ExceptionHandler(NotFoundExceptionException.class)
    public String error(Model model, NotFoundException ex) {

        model.addAttribute("id", ex.getId());
        return ("pagina_error");
    }
    
}    
