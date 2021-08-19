package br.com.uniacademia.cesIC.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class License implements Serializable{

	
	private static final long serialVersionUID = 746306310956218818L;
	
	private String key;
	private String name;
	private String spdx_id;
	private String url;
	private String node_id;

}
