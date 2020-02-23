package com.alifiezan.datafetchers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.alifiezan.model.Transaction;
import com.alifiezan.repository.TransactionRepository;

import graphql.schema.DataFetcher;

@Component
public class TransactionDataFetchers {

	@Autowired
	TransactionRepository transactionRepository;

	// retrieve all transaction record
	public DataFetcher<List<Transaction>> getAll() {

		return dataFetchingEnvironment -> {

			return (List<Transaction>) transactionRepository.findAll();
		};
	}

	// retrieve all transaction record by specify the page
	public DataFetcher<Page<Transaction>> getAllByPage() {

		return dataFetchingEnvironment -> {
			int page = dataFetchingEnvironment.getArgument("page");
			int size = dataFetchingEnvironment.getArgument("size");

			Pageable pageable = PageRequest.of(page, size);
			Page<Transaction> list = transactionRepository.findAll(pageable);

			return list;
		};
	}

	// retrieve record by customer id
	public DataFetcher<List<Transaction>> getByCustId() {

		return dataFetchingEnvironment -> {

			long match = dataFetchingEnvironment.getArgument("cust_id");

			List<Transaction> newList = new ArrayList<Transaction>();

			for (Transaction item : transactionRepository.findAll()) {

				if (item.getCust_id() == match) {
					newList.add(item);
				}
			}

			return newList;
		};
	}

	// retrieve record by account id
	public DataFetcher<List<Transaction>> getByAccNo() {

		return dataFetchingEnvironment -> {

			long match = dataFetchingEnvironment.getArgument("acc_no");

			List<Transaction> newList = new ArrayList<Transaction>();

			for (Transaction item : transactionRepository.findAll()) {

				if (item.getAcc_no() == match) {
					newList.add(item);
				}
			}

			return newList;
		};
	}

	// retrieve record by description (that contain the match argument)
	public DataFetcher<List<Transaction>> getByDesc() {
		return dataFetchingEnvironment -> {

			String match = dataFetchingEnvironment.getArgument("description");

			List<Transaction> newList = new ArrayList<Transaction>();

			for (Transaction item : transactionRepository.findAll()) {

				if (item.getDescription().contains(match)) {
					newList.add(item);
				}
			}

			return newList;
		};
	}

}
