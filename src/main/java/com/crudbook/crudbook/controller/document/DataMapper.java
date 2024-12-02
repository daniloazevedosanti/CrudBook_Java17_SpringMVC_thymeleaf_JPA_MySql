package com.crudbook.crudbook.controller.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.crudbook.crudbook.model.dtos.CreatedLivroDto;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

@Service
public class DataMapper {

    public Context setData(ArrayList<CreatedLivroDto> list) {

        Context context = new Context();

        Map<String, Object> data = new HashMap<>();

        data.put("projects", list);

        context.setVariables(data);

        return context;
    }
}
