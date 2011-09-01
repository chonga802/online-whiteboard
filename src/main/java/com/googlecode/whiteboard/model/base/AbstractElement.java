/*
* @author  Oleg Varaksin (ovaraksin@googlemail.com)
* $$Id$$
*/

package com.googlecode.whiteboard.model.base;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public abstract class AbstractElement
{
    private String uuid;

    public AbstractElement() {
    }

    public AbstractElement(String uuid) {
        this.uuid = uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public boolean equals(Object obj) {
        //return EqualsBuilder.reflectionEquals(this, obj);

        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass()) {
            return false;
        }

        return this.uuid.equals(((AbstractElement) obj).getUuid());
    }

    public int hashCode() {
        //return HashCodeBuilder.reflectionHashCode(this);

        return new HashCodeBuilder(17, 37).append(uuid).toHashCode();
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
