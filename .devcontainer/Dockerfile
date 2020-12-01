FROM mcr.microsoft.com/vscode/devcontainers/universal:linux

USER root

# [Option] Install Docker CLI
ARG INSTALL_DOCKER="false"
COPY library-scripts/docker-debian.sh /tmp/library-scripts/
RUN if [ "${INSTALL_DOCKER}" = "true" ]; then \
        rm -f /usr/local/share/docker-init.sh \
        && bash /tmp/library-scripts/docker-debian.sh "true" "/var/run/docker-host.sock" "/var/run/docker.sock" "codespace"; \
    fi \
    && rm -rf /var/lib/apt/lists/* /tmp/library-scripts/

COPY library-scripts/python-debian.sh /tmp/library-scripts/
RUN bash /tmp/library-scripts/python-debian.sh \
    && apt-get clean -y && rm -rf /var/lib/apt/lists/* /tmp/library-scripts

USER codespace

# Install gauge
SHELL ["/bin/bash", "-o", "pipefail", "-c"]
RUN curl -SsL https://downloads.gauge.org/stable | sh

# Check gauge installed successfully and install plugins
RUN gauge -v \
     && gauge install java \
     && gauge install js \
     && gauge install python \
     && gauge install ruby \
     && gauge install dotnet \
     && gauge install html-report \
     && gauge install screenshot \
     && gauge install spectacle \
     && gauge -v
