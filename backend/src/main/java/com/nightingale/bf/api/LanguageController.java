package com.nightingale.bf.api;

import com.nightingale.bf.model.api.LanguageRequest;
import com.nightingale.bf.model.api.LanguageResponse;
import com.nightingale.bf.service.LangService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class LanguageController {
    private final Collection<LangService> services;

    public LanguageController(Collection<LangService> services) {
        this.services = services;
    }

    @PostMapping("/{language}/execute")
    public LanguageResponse execute(@PathVariable String language,
                                    @RequestBody LanguageRequest request) {
        return new LanguageResponse(
            findService(language).execute(request.getCode(), request.getInput())
        );
    }

    @PostMapping("/{language}/build-source")
    public LanguageResponse buildSource(@PathVariable String language,
                                        @RequestBody LanguageRequest request) {
        return new LanguageResponse(
            findService(language).fromExecutionResult(request.getCode())
        );
    }

    @PostMapping("/{language}/transpile-to-bf")
    public LanguageResponse transpileTo(@PathVariable String language,
                                        @RequestBody LanguageRequest request) {
        return new LanguageResponse(
            findService(language).fromHighLevel(request.getCode())
        );
    }

    @PostMapping("/{language}/transpile-from-bf")
    public LanguageResponse transpileFrom(@PathVariable String language,
                                        @RequestBody LanguageRequest request) {
        return new LanguageResponse(
            findService(language).toHighLevel(request.getCode())
        );
    }

    private LangService findService(String language) {
        return services.stream()
            .filter(service -> service.getLangSpec().getKey().equals(language))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException(
                "Language '" + language + "' is not supported."));
    }
}