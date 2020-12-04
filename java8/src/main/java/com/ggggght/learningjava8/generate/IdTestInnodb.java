package com.ggggght.learningjava8.generate;

import java.io.Serializable;
import lombok.Data;

/**
 * id_test_innodb
 * @author 
 */
@Data
public class IdTestInnodb implements Serializable {
    private Integer id;

    private String name;

    private static final long serialVersionUID = 1L;
}