package com.formation.computerdatabase.core.domain;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QStat is a Querydsl query type for Stat
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QStat extends EntityPathBase<Stat> {

    private static final long serialVersionUID = 1030108157;

    public static final QStat stat = new QStat("stat");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> idComputer = createNumber("idComputer", Long.class);

    public final StringPath statType = createString("statType");

    public QStat(String variable) {
        super(Stat.class, forVariable(variable));
    }

    @SuppressWarnings("all")
    public QStat(Path<? extends Stat> path) {
        super((Class)path.getType(), path.getMetadata());
    }

    public QStat(PathMetadata<?> metadata) {
        super(Stat.class, metadata);
    }

}

