package com.formation.computerdatabase.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;
import com.mysema.query.types.path.PathInits;


/**
 * QComputer is a Querydsl query type for Computer
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QComputer extends EntityPathBase<Computer> {

    private static final long serialVersionUID = -2065125308;

    private static final PathInits INITS = PathInits.DIRECT;

    public static final QComputer computer = new QComputer("computer");

    public final QCompany company;

    public final DatePath<org.joda.time.LocalDate> discontinued = createDate("discontinued", org.joda.time.LocalDate.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<org.joda.time.LocalDate> introduced = createDate("introduced", org.joda.time.LocalDate.class);

    public final BooleanPath isVisible = createBoolean("isVisible");

    public final StringPath name = createString("name");

    public QComputer(String variable) {
        this(Computer.class, forVariable(variable), INITS);
    }

    @SuppressWarnings("all")
    public QComputer(Path<? extends Computer> path) {
        this((Class)path.getType(), path.getMetadata(), path.getMetadata().isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComputer(PathMetadata<?> metadata) {
        this(metadata, metadata.isRoot() ? INITS : PathInits.DEFAULT);
    }

    public QComputer(PathMetadata<?> metadata, PathInits inits) {
        this(Computer.class, metadata, inits);
    }

    public QComputer(Class<? extends Computer> type, PathMetadata<?> metadata, PathInits inits) {
        super(type, metadata, inits);
        this.company = inits.isInitialized("company") ? new QCompany(forProperty("company")) : null;
    }

}

