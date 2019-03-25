package br.com.listavip.docker.controller;

import br.com.alura.enviadorEmail.enviadorEmail.EmailService;
import br.com.listavip.docker.model.Convidado;
import br.com.listavip.docker.service.ConvidadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class ConvidadoController {

    @Autowired
    private ConvidadoService service;

    @GetMapping("/")
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    @RequestMapping("/listaconvidados")
    public ModelAndView listaConvidados(Convidado convidado){

        ModelAndView modelAndView = new ModelAndView("listaconvidados");
        Iterable<Convidado> convidados = service.obterTodos();
        modelAndView.addObject("convidados", convidados);

        return modelAndView;
    }

    @RequestMapping(value = "salvar", method = RequestMethod.POST )
    public ModelAndView salvar(@RequestParam("nome") String nome, @RequestParam("email")String email,
                         @RequestParam("telefone") String telefone){

        ModelAndView modelAndView = new ModelAndView("listaconvidados");
        Convidado novoConvidado = new Convidado(nome, email, telefone);
        service.salvar(novoConvidado);

        new EmailService().enviar(nome, email);

        Iterable<Convidado> convidados = service.obterTodos();
        modelAndView.addObject("convidados", convidados);
        return modelAndView;
    }

    @GetMapping("/listaconvidados/{id}")
    public ModelAndView findForId(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView("/listaconvidados");
        Optional<Convidado> convidados = service.obterPorId(id);
        modelAndView.addObject("convidados", convidados);
        return modelAndView;
    }

    @GetMapping("/{nome}")
    public ModelAndView findForName(@PathVariable("nome") String nome) {
        ModelAndView modelAndView = new ModelAndView("/listaconvidados");
        List<Convidado> convidados = service.obterPorNome(nome);
        modelAndView.addObject("convidados", convidados);
        return modelAndView;
    }

    @RequestMapping("/urlMagicaMaluca")
    public String urlMagica(){

        return "url executada";
    }
}
