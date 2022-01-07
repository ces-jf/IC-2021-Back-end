package br.com.uniacademia.cesIC.service;

import java.util.List;

import br.com.uniacademia.cesIC.models.Contributor;

public interface ContributoService {

    List<Contributor> include(List<Contributor> contributors);
}
