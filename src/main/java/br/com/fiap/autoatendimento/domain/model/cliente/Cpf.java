package br.com.fiap.autoatendimento.domain.model.cliente;

import br.com.fiap.autoatendimento.domain.exceptions.ValidationException;

// A validação do CPF será trocada por uma lib de terceiros

public class Cpf {

	private final String value;

	public Cpf(String value) {
		if (!validate(value)) {
			throw new ValidationException("CPF inválido");
		}
		this.value = value;
	}

	private boolean validate(String cpf) {
		cpf = clean(cpf);
		if (isValidLength(cpf)) return false;
		if (hasAllDigitsEqual(cpf)) return false;
		int digito1 = calculateDigit(cpf, 10);
		int digito2 = calculateDigit(cpf, 11);
		return extractCheckDigit(cpf).equals("" + digito1 + digito2);
	}

	public String getValue() {
		return value;
	}

	private String clean(String cpf) {
		return cpf.replaceAll("\\D", "");
	}

	private boolean isValidLength(String cpf) {
		return cpf.length() != 11;
	}

	private boolean hasAllDigitsEqual(String cpf) {
		char firstDigit = cpf.charAt(0);
		return cpf.chars().allMatch(digit -> digit == firstDigit);
	}

	private int calculateDigit(String cpf, int factor) {
		int total = 0;
		for (char digit : cpf.toCharArray()) {
			if (factor > 1) total += Character.getNumericValue(digit) * factor--;
		}
		int rest = total % 11;
		return (rest < 2) ? 0 : 11 - rest;
	}

	private String extractCheckDigit(String cpf) {
		return cpf.substring(9);
	}

}