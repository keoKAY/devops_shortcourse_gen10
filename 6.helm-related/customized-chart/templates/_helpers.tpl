{{- define "dpl-selector" }}
  selector:
    matchLabels:
      app: {{ .Values.appName.app }}
  template:
    metadata:
      labels:
        app: {{ .Values.appName.app }}
{{- end  }}


{{- define "volume-config" }}
      {{- if .Values.container.volumes }}
      volumes: 
        {{- toYaml .Values.container.volumes | nindent 8 }}
      {{- end  }}
{{- end }}