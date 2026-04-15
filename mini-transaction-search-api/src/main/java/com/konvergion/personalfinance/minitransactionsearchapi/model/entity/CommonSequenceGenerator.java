package com.konvergion.personalfinance.minitransactionsearchapi.model.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.LongType;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

public class CommonSequenceGenerator extends SequenceStyleGenerator {
    /*
     * We need to define the 'PREFIX' and we don't hard code that here
     * because we want it to be passed from the entities, so that the same
     * sequence generator can be used for many entities
     */
    public static final String VALUE_PREFIX_PARAM = "valuePrefix";
    public static final String VALUE_PREFIX_DEFAULT = "";
    private String valuePrefix;

    /*
     * Number format here defines the format of the number part of the sequence
     * like 10 digits or  12 digits etc.
     */
    public static final String NUMBER_FORMAT_PARAM = "numberFormat";
    public static final String NUMBER_FORMAT_DEFAULT = "%d";
    private String numberFormat;

    /*
     * This method is called when this object is being instantiated
     * (non-Javadoc)
     * @see org.hibernate.id.enhanced.SequenceStyleGenerator#configure(org.hibernate.type.Type, java.util.Properties, org.hibernate.service.ServiceRegistry)
     */
    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) {
        /*
         * Within this method, we need to do two things
         * 1) Call the super class method and set the type parameter to long type
         *
         * This is necessary because the sequence value will be part of a string
         * e.g. 0000012 will be part of the string ACC0000012
         *
         * But Hibernate can't handle string based sequences, so we need to tell Hibernate to
         * generate a sequence value of type long and convert it afterwards.
         */
        super.configure(LongType.INSTANCE, params, serviceRegistry);

        /*
         * The second thing is
         * 2) Read configuration parameters provided for this generator
         * We're using the ConfigurationHelper class from org.hibernate.internal.util.config
         *
         * All parameters come as part of the params object
         *
         * Here we're telling Hibernate to read the parameter named 'valuePrefix' provided
         * by the entity from the Properties 'params' and if nothing of sorts have
         * been provided then use the default value
         *
         * Same for the number format
         */
        valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAM, params, VALUE_PREFIX_DEFAULT);

        numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAM, params, NUMBER_FORMAT_DEFAULT);

        //So there it is...!! Now both of these configured parameters will be used by the generate method
    }


    /* This method gets called when Hibernate needs a new sequence value to persist an entity
     * to which this class has been tied to as a sequence generator
     *
     *
     * (non-Javadoc)
     * @see org.hibernate.id.enhanced.SequenceStyleGenerator#generate(org.hibernate.engine.spi.SharedSessionContractImplementor, java.lang.Object)
     */
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        /*
         * Here we're doing some pretty simple things
         * 1) We're calling the generate method from the super class to
         * get the next value from the sequence
         * 2) Then transform that value into the configured format
         * which in this case is a string
         * 3) And finally we add it to the defined prefix
         */
        return
                valuePrefix + String.format(numberFormat, super.generate(session, object));
    }
}
