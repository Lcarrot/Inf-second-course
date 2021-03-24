package ru.itis.tyshenko.controller;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import ru.itis.tyshenko.dto.Dto;
import ru.itis.tyshenko.form.Form;
import ru.itis.tyshenko.service.Service;
import ru.itis.tyshenko.util.BindingResultMessages;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Map;
import java.util.Optional;

@Component
public class EntityController {

    public <T extends Dto,M extends Form> String saveNewUser(HttpServletRequest request, @Valid M user, Service<T, M> service, BindingResult result, String errorCode,
                                                             Map<String, Object> modelAttributes, Model model, BindingResultMessages bindingResultMessages, String view, String redirect) {
        Optional<String> error = bindingResultMessages.getMessageFromError(result, errorCode);
        if (error.isPresent()) {
            for (Map.Entry<String, Object> attribute: modelAttributes.entrySet()) {
                model.addAttribute(attribute.getKey(), attribute.getValue());
            }
            model.addAttribute("error", error.get());
            return view;
        }
        Object object = service.add(user).get();
        request.getSession().setAttribute("user", object);
        return redirect;
    }
}
