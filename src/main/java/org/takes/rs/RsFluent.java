/**
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Yegor Bugayenko
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package org.takes.rs;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import lombok.EqualsAndHashCode;
import org.takes.Response;

/**
 * Fluent response.
 *
 * @author Yegor Bugayenko (yegor@teamed.io)
 * @version $Id$
 * @since 0.1
 */
@EqualsAndHashCode(of = "origin")
public final class RsFluent implements Response {

    /**
     * Original response.
     */
    private final transient Response origin;

    /**
     * Ctor.
     */
    public RsFluent() {
        this(new RsEmpty());
    }

    /**
     * Ctor.
     * @param res Original response
     */
    public RsFluent(final Response res) {
        this.origin = res;
    }

    @Override
    public List<String> head() throws IOException {
        return this.origin.head();
    }

    @Override
    public InputStream body() throws IOException {
        return this.origin.body();
    }

    /**
     * With this status code.
     * @param code Status code
     * @return New fluent response
     */
    public RsFluent withStatus(final int code) {
        return new RsFluent(new RsWithStatus(this.origin, code));
    }

    /**
     * With this header.
     * @param header The header
     * @return New fluent response
     */
    public RsFluent withHeader(final String header) {
        return new RsFluent(new RsWithHeader(this.origin, header));
    }

    /**
     * With this header.
     * @param key Key
     * @param value Value
     * @return New fluent response
     */
    public RsFluent withHeader(final String key, final String value) {
        return new RsFluent(new RsWithHeader(this.origin, key, value));
    }

    /**
     * With this content type.
     * @param ctype Content type
     * @return New fluent response
     */
    public RsFluent withContentType(final String ctype) {
        return new RsFluent(new RsWithType(this.origin, ctype));
    }

    /**
     * With this body.
     * @param body Body
     * @return New fluent response
     */
    public RsFluent withBody(final String body) {
        return new RsFluent(new RsWithBody(this.origin, body));
    }

    /**
     * With this body.
     * @param body Body
     * @return New fluent response
     */
    public RsFluent withBody(final byte[] body) {
        return new RsFluent(new RsWithBody(this.origin, body));
    }

    /**
     * With this body.
     * @param body Body
     * @return New fluent response
     */
    public RsFluent withBody(final InputStream body) {
        return new RsFluent(new RsWithBody(this.origin, body));
    }

}